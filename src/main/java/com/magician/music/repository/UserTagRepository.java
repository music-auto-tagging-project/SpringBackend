package com.magician.music.repository;

import com.magician.music.domain.Tag;
import com.magician.music.domain.TagType;
import com.magician.music.domain.UserTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserTagRepository {
    private final EntityManager em;

    public void save(UserTag userTag){
        em.persist(userTag);
    }

    public List<UserTag> findTagListByUserId(Long userId) {
        return em.createQuery("select ut from UserTag ut " +
                "where ut.user.id = :userId", UserTag.class)
                .setParameter("userId", userId)
                .getResultList();
    }


    public UserTag findOne(Long userId, Long tagId) {
        return em.createQuery("select ut from UserTag ut " +
                "where ut.user.id = :userId and ut.tag.id = :tagId",
                UserTag.class)
                .setParameter("userId", userId)
                .setParameter("tagId", tagId)
                .getSingleResult();
    }

    public UserTag findOneByUserIdAndTagName(Long userId, String tagName) {
        try {
            return em.createQuery("select ut from UserTag ut " +
                                    "where ut.user.id = :userId and ut.tag.name = :tagName",
                            UserTag.class)
                    .setParameter("userId", userId)
                    .setParameter("tagName", tagName)
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
}
