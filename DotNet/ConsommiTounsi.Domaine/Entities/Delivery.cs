using ConsommiTounsi.Domaine.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities
{
    public class Delivery
    {
        public int? IdDelivery { get; set; }
        public User Livreur { get; set; }
        public float Cout { get; set; }
        public String Etat { get; set; }
        public DateTime? DeliveryDate { get; set; }

    }
}
