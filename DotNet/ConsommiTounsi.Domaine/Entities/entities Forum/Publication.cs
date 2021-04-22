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
        public virtual User userp { get; set; }

         public virtual ICollection<Comment> comments { get; set; }
        public virtual ICollection<RatingPub> ratPub { get; set; }
        
    }
}
