using ConsommiTounsi.Domaine.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities
{
    public class Event
    {
        public int? Id { get; set; }
        public float Cout { get; set; }
        public DateTime DateEvent { get; set; }
        public string Titre { get; set; }
        public string Description { get; set; }


        //Prop de navigation
        public User Fondateur { get; set; }


    }
}
