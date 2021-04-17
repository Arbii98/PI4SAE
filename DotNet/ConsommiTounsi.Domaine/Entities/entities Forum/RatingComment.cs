using ConsommiTounsi.Domaine.entities;
using ConsommiTounsi.Domaine.entities.entities_Forum;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.Entities.entities_Forum
{
    public class RatingComment
    {
        public int idR { get; set; }
        public TypeRating typerating { get; set; }
        public virtual Comment comment { get; set; }
        public virtual User users { get; set; }

    }
}
