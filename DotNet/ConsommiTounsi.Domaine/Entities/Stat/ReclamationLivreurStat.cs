using ConsommiTounsi.Domaine.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities.Stat
{
    public class ReclamationLivreurStat
    {
        public int Count { get; set; }
        public User Livreur { get; set; }
    }
}
