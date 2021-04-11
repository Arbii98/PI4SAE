using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Data.Infrastructures
{
    public interface IDataBaseFactory : IDisposable
    {
        MyContext MyContext { get; }
    }
}
