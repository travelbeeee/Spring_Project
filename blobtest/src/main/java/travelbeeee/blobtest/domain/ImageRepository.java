package travelbeeee.blobtest.domain;

import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ImageRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Image image) {
        em.persist(image);
    }

    public Image find(Long id){
        return em.find(Image.class, id);
    }

    public List<Image> findAll(){
        return em.createQuery("select i from Image i").getResultList();
    }
}
