using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.entities.entities_Forum
{
    public class Message
    {
        public int idMessage { get; set; }

        public string content { get; set; }

        public DateTime date { get; set; }


        public User sender { get; set; }

        public User receiver { get; set; }

    }
}
