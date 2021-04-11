using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Data
{
    public class MyContext : DbContext
    {
        public MyContext() : base("name = ")
        {

        }
    }
}
