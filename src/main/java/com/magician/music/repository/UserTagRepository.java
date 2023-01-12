package com.magician.music.repository;

import com.magician.music.domain.Tag;
import com.magician.music.domain.TagType;
import com.magician.music.domain.User;
import com.magician.music.domain.UserTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserTagRepository{
    private final EntityManager em;

    public void save(UserTag userTag){
        em.persist(userTag);
    }



    public List<UserTag> findAllByUserId(Long userId) {
        return em.createQuery("select ut from UserTag ut " +
                "where ut.user.id = :userId", UserTag.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    public List<UserTag> findAllByUserIdAndTagType(Long userId, TagType tagType) {
        return em.createQuery("select ut from UserTag ut " +
                        "where ut.user.id = :userId " +
                        "and ut.tagType = :tagType", UserTag.class)
                .setParameter("userId", userId)
                .setParameter("tagType", tagType)
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

    public UserTag findByUserIdAndTagName(Long userId, String tagName) {
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


    public void delete(User user, UserTag userTag) {
        try{
            em.createQuery("delete from UserTag t where t.tag = :tag and t.user = :user")
                    .setParameter("tag", userTag.getTag())
                    .setParameter("user", user)
                    .executeUpdate();

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
