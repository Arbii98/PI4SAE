using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.entities.entities_Forum
{
    public class CommentPk
    {
        public int idPub { get; set; }
        public int idUser { get; set; }
        public DateTime datecreation { get; set; }

    }
}
