using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.entities.entities_Forum
{
    public class Publication
    {
        public int idPub { get; set; }
        public string title { get; set; }
        public string description { get; set; }
        public DateTime dateCreation { get; set; }
        public virtual User users { get; set; }
        virtual public ICollection<Comment> comments { get; set; }
        
    }
}
