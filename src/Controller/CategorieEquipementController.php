<?php

namespace App\Controller;

use App\Entity\CategorieEquipement;
use App\Form\CategorieEquipementType;
use App\Repository\CategorieEquipementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/categorie/equipement")
 */
class CategorieEquipementController extends AbstractController
{
    /**
     * @Route("/affiche", name="categorie_equipement_index", methods={"GET"})
     */
    public function index(CategorieEquipementRepository $categorieEquipementRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $categorie_equipement=$categorieEquipementRepository->findAll();
        $categorie_equipement = $paginator->paginate(
            $categorie_equipement, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            5 /*limit per page*/
        );
        return $this->render('categorie_equipement/index.html.twig', [
            'categorie_equipements' => $categorie_equipement,
        ]);
    }
    /**
     * param CategorieEquipementRepository $Repository
     * return use Symfony\Component\HttpFoundation\Response;
     * @Route("/display", name="categorie_equipement_indexback", methods={"GET"})
     */
    public function indexback(CategorieEquipementRepository $Repository)
    {
        $categorie=$Repository->findAll ();
        return $this->render('categorie_equipement/indexback.html.twig', [
            'categorie_equipements' => $categorie,
        ]);
    }

    /**
     * @Route("/new", name="categorie_equipement_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $categorieEquipement = new CategorieEquipement();
        $form = $this->createForm(CategorieEquipementType::class, $categorieEquipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($categorieEquipement);
            $entityManager->flush();

            return $this->redirectToRoute('categorie_equipement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('categorie_equipement/new.html.twig', [
            'categorie_equipement' => $categorieEquipement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="categorie_equipement_show", methods={"GET"})
     */
    public function show(CategorieEquipement $categorieEquipement): Response
    {
        return $this->render('categorie_equipement/show.html.twig', [
            'categorie_equipement' => $categorieEquipement,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="categorie_equipement_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, CategorieEquipement $categorieEquipement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CategorieEquipementType::class, $categorieEquipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('categorie_equipement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('categorie_equipement/edit.html.twig', [
            'categorie_equipement' => $categorieEquipement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="categorie_equipement_delete", methods={"POST"})
     */
    public function delete(Request $request, CategorieEquipement $categorieEquipement, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorieEquipement->getId(), $request->request->get('_token'))) {
            $entityManager->remove($categorieEquipement);
            $entityManager->flush();
        }

        return $this->redirectToRoute('categorie_equipement_index', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/AllCategories", name="AllCategories")
     */
    public function AllCategories(CategorieEquipementRepository $categorieEquipementRepository,SerializerInterface $serializer):Response
    {
        $categorie_equipement=$categorieEquipementRepository->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($categorie_equipement,'json',['categorie_equipement'=>"post:read"]);
        return new JsonResponse($formatted);
    }  
    /**
   * @Route("/updatecategoryJSON/{id}", name="updateRoleJSON")
*/
public function updatecategoryJSON ( Request $request, NormalizerInterface $Normalizer, $id)

{ 
    $em = $this->getDoctrine()->getManager(); 
    $categorieEquipement = $em->getRepository(CategorieEquipement::class)->find($id);
    $nom_categorie_equipement = $request->query->get("nom_categorie_equipement");      
    $categorieEquipement->setNomCategorieEquipement($nom_categorie_equipement);
    $em->flush();
    $jsonContent= $Normalizer->normalize($categorieEquipement,'json',['groups'=>"post:read"]);
    return new Response("Information updated successfully".json_encode($jsonContent));
}
 /**
* @Route("/deletecategoryJSON/{id}", name="deleteRoleJSON")
*/
public function deletecategoryJSON(Request $request, NormalizerInterface $Normalizer, $id)
{$em = $this->getDoctrine()->getManager(); 
$categorieEquipement = $em->getRepository (CategorieEquipement::class)->find($id); 
$em->remove($categorieEquipement); $em->flush(); 
$jsonContent= $Normalizer->normalize($categorieEquipement,'json',['groups'=>'post:read']); 
return new Response("Category deleted successfully".json_encode($jsonContent));
}
    /**
    * @Route("/Addcategory", name="Addcategory")

    */
    public function AddCategoryJSON(Request $request,NormalizerInterface $Normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $Role =new CategorieEquipement();
        $Role->setNomCategorieEquipement($request->get('nom_categorie_equipement'));
        $em->persist($Role);
        $em->flush();
 
        $jsonContent= $Normalizer->normalize($Role,'json',['groups'=>"post:read"]);
        return new Response(json_encode($jsonContent));;
    }
   
}
