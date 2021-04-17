using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.entities.entities_Forum
{
    public class Comment
    {
        public CommentPk commentPk { get; set; }
        public string description { get; set; }

        public virtual User user { get; set; }
        public virtual Publication publication { get; set; }
        

    }
}
