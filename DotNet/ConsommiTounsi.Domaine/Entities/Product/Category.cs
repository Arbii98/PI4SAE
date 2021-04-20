using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsommiTounsi.Domaine.Entities.Wided;

namespace ConsommiTounsi.Domaine.Entities.Wided
{
    public class Category
    {
        public int? CategoryId { get; set; }
        [Display(Name = "Name")]
        public string nameCategory { get; set; }
        [Display(Name = "Description")]
        public string descriptionCategory { get; set; }
        public virtual ICollection<Product> products { get; set; }
    }
}