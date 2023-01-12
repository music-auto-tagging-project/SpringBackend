package com.magician.music.repository.query;

import com.magician.music.domain.Music;
import com.magician.music.domain.PlaylistType;
import com.magician.music.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository("v2.MusicQueryRepository")
@RequiredArgsConstructor
public class MusicQueryRepository {

    private final EntityManager em;

    public List<MusicQueryDto> getMusicArtistList(List<MusicQueryDto> musicDtoList){
        musicDtoList.forEach( md -> {
            md.setMusicArtist(findRecommendMusicArtistList(md.getMusicId()).stream()
                    .map(a->a.getName()).collect(Collectors.toList()));
        });

        return musicDtoList;
    }

    public List<ArtistQueryDto> findRecommendMusicArtistList(Long musicId){
        return em.createQuery(
                "select new com.magician.music.repository.query.ArtistQueryDto(a.artist.id, a.artist.name)" +
                        "from Music m" +
                        " join m.artistList a" +
                        " where m.id = :musicId"
                , ArtistQueryDto.class).setParameter("musicId", musicId).getResultList();
    }


    /*return 값 Music으로 바꿔서 다시 작성해보자*/
    public List<MusicQueryDto> findMusicListByPlaylistType(Long id, PlaylistType type){
        return em.createQuery(
                "select new com.magician.music.repository.query.MusicQueryDto(m.id, a.imagePath, m.title)" +
                        " from Playlist p" +
                        " join p.user u" +
                        " join p.playlistMusicList ml" +
                        " join ml.music m" +
                        " join m.album a" +
                        " where u.id = :id" +
                        " and p.playlistType = :type"
                , MusicQueryDto.class)
                .setParameter("id", id)
                .setParameter("type", type)
                .getResultList();

    }
    public List<MusicQueryDto> findPlayedMusicList(Long id, PlaylistType type){
        return em.createQuery(
                        "select new com.magician.music.repository.query.MusicQueryDto(m.id, a.imagePath, m.title)" +
                                " from Playlist p" +
                                " join p.user u" +
                                " join p.playlistMusicList ml" +
                                " join ml.music m" +
                                " join m.album a" +
                                " where u.id = :id" +
                                " and p.playlistType = :type " +
                                " group by m.id " +
                                " order by max(ml.id) desc"
                        , MusicQueryDto.class)
                .setParameter("id", id)
                .setParameter("type", type)
                .getResultList();

    }

    public List<MusicQueryDto> findAllMusicName(){
        return em.createQuery(
                        "select new com.magician.music.repository.query.MusicQueryDto(m.id, a.imagePath, m.title) " +
                                "from Music m " +
                                "join m.album a"
                        , MusicQueryDto.class)
                .getResultList();
    }

    public List<MusicQueryDto> findAllMusicNameByName(String content) {
        return em.createQuery(
                        "select new com.magician.music.repository.query.MusicQueryDto(m.id, a.imagePath, m.title) " +
                                "from Music m " +
                                "join m.album a " +
                                "where m.title like concat('%', :title, '%')"
                        , MusicQueryDto.class)
                .setParameter("title", content)
                .getResultList();
    }

    public List<Music> findAllByTag(Tag tag){
        return em.createQuery(
                        "select distinct m " +
                                "from MusicTag mt " +
                                "join  mt.music m " +
                                "where mt.tag.name = :tagName"
                        , Music.class)
                .setParameter("tagName", tag.getName())
                .getResultList();
    }
}
