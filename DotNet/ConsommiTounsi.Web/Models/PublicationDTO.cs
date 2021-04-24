using ConsommiTounsi.Domaine.entities.entities_Forum;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ConsommiTounsi.Web.Models
{
    public class PublicationDTO
    {
        public Publication publication;
        public ratPubVm ratPubVm;
   

        public PublicationDTO(Publication p, ratPubVm result)
        {
            this.publication = p;
            this.ratPubVm = result;
        }
    }
}