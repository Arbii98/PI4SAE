using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.entities.entities_Forum
{
    public class RatingPub
    {
        public int idRp { get; set; }
        public ratPub rat { get; set; }
        public virtual User users { get; set; }
        public virtual Publication pub { get; set; }
    }
}
