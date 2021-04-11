using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsommiTounsi.Data.Infrastructures
{
    public interface IUnitOfWork : IDisposable
    {
        void Commit();
        //void Dispose(); Hidden from IDisposable interface
        IRepositoryBase<T> getRepository<T>() where T : class;
    }
}
