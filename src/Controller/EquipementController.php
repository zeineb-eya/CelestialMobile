<?php

namespace App\Controller;

use App\Entity\Equipement;
use App\Form\EquipementType;
use App\Repository\EquipementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Knp\Component\Pager\PaginatorInterface;
use Mediumart\Orange\SMS\SMS;
use Mediumart\Orange\SMS\Http\SMSClient;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;




/**
 * @Route("/equipementt")
 */
class EquipementController extends AbstractController
{
    /**
     * @Route("/affiche", name="equipement_index", methods={"GET"})
     */
    public function index(EquipementRepository $equipementRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $equipement=$equipementRepository->findAll();
        $equipement = $paginator->paginate(
            $equipement, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            3 /*limit per page*/
        );

        return $this->render('equipement/index.html.twig', [
            'equipements' => $equipement,
        ]);
    }
    /**
     * @Route("/list", name="equipement_list", methods={"GET"})
     */
    public function list(EquipementRepository $equipementRepository, Request $request): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $equipement=$equipementRepository->findAll();
        
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('equipement/list.html.twig', [
            'equipements' => $equipement,
        ]);
        
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
        
        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A3', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Equipements.pdf", [
            "Attachment" => true
        ]);

    
        
    }
    /**
     * param EquipementRepository $Repository
     * return use Symfony\Component\HttpFoundation\Response;
     * @Route("/display", name="equipement_indexback", methods={"GET"})
     */
    public function indexback(EquipementRepository $Repository)
    {
        $equipement=$Repository->findAll ();
        return $this->render('equipement/indexback.html.twig', [
            'equipements' => $equipement,
        ]);
    }
   

    /**
     * @Route("/new", name="equipement_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $equipement = new Equipement();
        $form = $this->createForm(EquipementType::class, $equipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$equipement->getImageEquipement();
            $filename=md5(uniqid()).'.'.$file->guessExtension();
            
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $equipement->setImageEquipement($filename);
            $entityManager->persist($equipement);
            $entityManager->flush();
            //$client = SMSClient::getInstance('2Yf3CBy0mWhiS0TcVCWonAOkEUXs6cLF', 'Bgflgfsi6lEN1e2V');
            //$sms = new SMS($client);
            //$sms->message('Une Categorie a ete ajoutee . Consulez notre site !')
            //->from('+21627300520')
            //>to($User->getNumTel()) on n a pas fais ce choix a cause du cout SMS
                   
            //->to('+21628327313')
            //->send();

            return $this->redirectToRoute('equipement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('equipement/new.html.twig', [
            'equipement' => $equipement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="equipement_show", methods={"GET"}, requirements={"id":"\d+"}))
     */
    public function show(Equipement $equipement): Response
    {
        return $this->render('equipement/show.html.twig', [
            'equipement' => $equipement,
        ]);
    }
    

    /**
     * @Route("/{id}/edit", name="equipement_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Equipement $equipement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EquipementType::class, $equipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$equipement->getImageEquipement();
            $filename=md5(uniqid()).'.'.$file->guessExtension();
            
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $equipement->setImageEquipement($filename);
   
            $entityManager->flush();

            return $this->redirectToRoute('equipement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('equipement/edit.html.twig', [
            'equipement' => $equipement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="equipement_delete", methods={"POST"})
     */
    public function delete(Request $request, Equipement $equipement, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$equipement->getId(), $request->request->get('_token'))) {
            $entityManager->remove($equipement);
            $entityManager->flush();
        }

        return $this->redirectToRoute('equipement_index', [], Response::HTTP_SEE_OTHER);
    }
    
    /**
   * Creates a new ActionItem entity.
   *
   * @Route("/search", name="ajax_searchh")
   * @Method("GET")
   */
    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $equipement = $em->getRepository(Equipement::class)->findEntitiesByString($requestString);
        if (!$equipement) {
            $result['equipements']['error'] = "Equipement introuvable 🙁 ";
        } else {
            $result['equipements'] = $this->getRealEntities($equipement);
        }
        return new Response(json_encode($result));
    }
    

  public function getRealEntities($equipement){

      foreach ($equipement as $equipement){
          $realEntities[$equipement->getId()] = [$equipement->getNomEquipement() ,$equipement->getEtatEquipement(),$equipement->getDescriptionEquipement() ,$equipement->getCategorieEquipement(),$equipement->getImageEquipement()];
      }

      return $realEntities;
  }
  /**
     * @Route("/AllEquipments", name="AllEquipments")
     */
    public function AllEquipments(EquipementRepository $equipementRepository,SerializerInterface $serilazer):Response
    {
        $equipement=$equipementRepository->findAll();
        $json= $serilazer->normalize($equipement,'json',['equipement'=>"post:read"]);
        return new JsonResponse($json);
    }
    /**
    * @Route("/AddEquipment", name="AddEquipment")
    */
   public function AddEquipment(Request $request,NormalizerInterface $Normalizer)
   {
       $em = $this->getDoctrine()->getManager();
       $equipement = new Equipement();
       $equipement->setNomEquipement($request->get('nom_equipement'));
       $equipement->setEtatEquipement($request->get('etat_equipement'));
       $equipement->setDescriptionEquipement($request->get('description_equipement'));
       $nom=$request->get('categorieEquipement');

       //$equipement->setCategorieEquipement->setNomCategorieEquipement($request->get('categorieEquipement'));

       $category=new CategorieEquipement;
       $category->setNomCategorieEquipement($request->get('categorieEquipement'));
       $equipement->setCategorieEquipement($category);
       $equipement->setImageEquipement($request->get('image_equipement'));
       $em->persist($category);
       $em->persist($equipement);
       $em->flush();

       $jsonContent= $Normalizer->normalize($equipement,'json',['groups'=>"post:read"]);
       return new Response("An equipment has been added");;
   }
    
 /**
   * @Route("/updateEquipmentJSON/{id}", name="updateEquipmentJSON")
*/
public function updateEquipmentJSON ( Request $request, NormalizerInterface $Normalizer, $id)

{
    $em = $this->getDoctrine()->getManager(); 
    $equipement = $em->getRepository(Equipement::class)->find($id);
    $equipement->setNomEquipement($request->get('nom_equipement'));
       $equipement->setEtatEquipement($request->get('etat_equipement'));
       $equipement->setDescriptionEquipement($request->get('description_equipement'));
        $nom=$request->get('categorieEquipement');
          $category=new CategorieEquipement;
         $category->setNomCategorieEquipement($nom);

     $equipement->setCategorieEquipement($category);
       $equipement->setImageEquipement($request->get('image_equipement'));
       $em->persist($category);
    $em->flush();
    $jsonContent= $Normalizer->normalize($equipement,'json',['groups'=>"equipement:read"]);
    return new Response("Information updated successfully".json_encode($jsonContent));
}
    /**
* @Route("/deleteEquipmentJSON/{id}", name="deleteEquipmentJSON")
*/
public function deleteEquipmentJSON(Request $request, NormalizerInterface $Normalizer, $id)
{$em = $this->getDoctrine()->getManager(); 
$Equipement = $em->getRepository (Equipement::class)->find($id); 
$em->remove($Equipement); $em->flush(); 
$jsonContent= $Normalizer->normalize($Equipement,'json',['groups'=>'Equipement:read']); 
return new Response("Equipment deleted successfully".json_encode($jsonContent));
}
    

}
