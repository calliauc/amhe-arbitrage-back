package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Tag;

import java.util.List;

@ApplicationScoped
public class TagRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Tag> getTags() {
        return em.createQuery("from Tag", Tag.class).getResultList();
    }

    @Transactional
    public List<Tag> getListTags(final List<Long> ids) {
        return em.createQuery("from Tag where id IN (:ids)", Tag.class)
                .setParameter("ids", ids).getResultList();
    }

    @Transactional
    public Tag getTagById(final Long id) {
        return em.find(Tag.class, id);
    }

    @Transactional
    public Tag createTag(final Tag nouveauTag) {
        return em.merge(nouveauTag);
    }

    @Transactional
    public Tag editTag(final Tag nouveauTag) {
        return em.merge(nouveauTag);
    }

    @Transactional
    public void deleteTag(final Long id) {
        Tag tagASpprimer = this.getTagById(id);
        em.remove(tagASpprimer);
    }

}
