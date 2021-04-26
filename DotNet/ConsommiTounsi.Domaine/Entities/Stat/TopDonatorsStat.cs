using ConsommiTounsi.Domaine.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities.Stat
{
    public class TopDonatorsStat
    {
        public float Montant { get; set; }
        public User Client { get; set; }
    }
}
