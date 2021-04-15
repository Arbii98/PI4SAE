using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities.Wided
{
    public class Marque
    {
        public int MarqueId { get; set; }
        public string nameMarque { get; set; }
        public string descriptionMarque { get; set; }
        public virtual ICollection<Product> products { get; set; }
    }
}
