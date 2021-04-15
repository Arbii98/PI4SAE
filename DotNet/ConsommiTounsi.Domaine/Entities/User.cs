using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Domaine.entities
{
    public class User
    {
        public int id { get; set; }
        public int banned { get; set; }
        public string name { get; set; }
        public string image { get; set; }

        public User(int id, string name, string image)
        {
            this.id = id;
            this.name = name;
            this.image = image;
        }


    }
}