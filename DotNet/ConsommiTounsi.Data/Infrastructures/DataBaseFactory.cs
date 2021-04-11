using ConsommiTounsi.Data.Infrastructures;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Data.Infrastructures
{
    public class DataBaseFactory : Disposable, IDataBaseFactory
    {
        private MyContext dataContext = null;
        public MyContext MyContext { get { return dataContext; } }
        public DataBaseFactory()
        {
            dataContext = new MyContext();
        }
        protected override void DisposeCore()
        {
            if (dataContext != null) dataContext.Dispose();
        }
    }
}
