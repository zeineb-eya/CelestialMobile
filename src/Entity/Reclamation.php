<?php

namespace App\Entity;

use App\Repository\ReclamationRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=ReclamationRepository::class)
 */
class Reclamation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("reclamation:read")
     */
    private $id;


    /**
     * @ORM\Column(type="text")
     * 
     * @Assert\NotBlank(message="Do not leave empty"),
     * @Assert\Length(
     * min = 10,
     * max = 100,
     * minMessage = "Le description_offre doit comporter au moins {{ limit }} caractères",
     * maxMessage = "Le description_offre doit comporter au plus {{ limit }} caractères"
     * )
     * @Groups("reclamation:read")
     
     */



    private $description_reclamation;

    /**
     * @ORM\Column(type="date")
    * @Assert\Date()
     * @Assert\GreaterThan("Yesterday")
     * @Assert\LessThan("tomorrow")
     * @Groups("reclamation:read")
     
     */
    private $date_reclamation;


    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="Reclamation")
     * @ORM\JoinColumn(nullable=false)
     */
    private $user;

    public function getId(): ?int
    {
        return $this->id;
    }


    public function getDescriptionReclamation(): ?string
    {
        return $this->description_reclamation;
    }

    public function setDescriptionReclamation(string $description_reclamation): self
    {
        $this->description_reclamation = $description_reclamation;

        return $this;
    }

    public function getDateReclamation(): ?\DateTimeInterface
    {
        return $this->date_reclamation;
    }

    public function setDateReclamation(\DateTimeInterface $date_reclamation): self
    {
        $this->date_reclamation = $date_reclamation;

        return $this;
    }



    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }
}