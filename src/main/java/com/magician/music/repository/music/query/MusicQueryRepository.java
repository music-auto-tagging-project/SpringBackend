package com.magician.music.repository.music.query;

import com.magician.music.domain.Artist;
import com.magician.music.domain.Music;
import com.magician.music.domain.PlaylistType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MusicQueryRepository {

    private final EntityManager em;

    public List<MusicDto> getMusicArtistList(List<MusicDto> musicDtoList){
        musicDtoList.forEach( md -> {
            md.setMusicArtist(findRecommendMusicArtistList(md.getMusicId()).stream()
                    .map(a->a.getName()).collect(Collectors.toList()));
        });

        return musicDtoList;
    }

    public List<ArtistDto> findRecommendMusicArtistList(Long musicId){
        return em.createQuery(
                "select new com.magician.music.repository.music.query.ArtistDto(a.artist.name)" +
                        "from Music m" +
                        " join m.artistList a" +
                        " where m.id = :musicId"
                , ArtistDto.class).setParameter("musicId", musicId).getResultList();
    }


    /*return 값 Music으로 바꿔서 다시 작성해보자*/
    public List<MusicDto> findMusicListByPlaylistType(Long id, PlaylistType type){
        return em.createQuery(
                "select new com.magician.music.repository.music.query.MusicDto(m.id, a.imagePath, m.title)" +
                        " from Playlist p" +
                        " join p.user u" +
                        " join p.playlistMusicList ml" +
                        " join ml.music m" +
                        " join m.album a" +
                        " where u.id = :id" +
                        " and p.playlistType = :type"
                , MusicDto.class)
                .setParameter("id", id)
                .setParameter("type", type)
                .getResultList();

    }
    public List<MusicDto> findPlayedMusicList(Long id, PlaylistType type){
        return em.createQuery(
                        "select distinct new com.magician.music.repository.music.query.MusicDto(m.id, a.imagePath, m.title)" +
                                " from Playlist p" +
                                " join p.user u" +
                                " join p.playlistMusicList ml" +
                                " join ml.music m" +
                                " join m.album a" +
                                " where u.id = :id" +
                                " and p.playlistType = :type " +
                                " order by ml.id desc"
                        , MusicDto.class)
                .setParameter("id", id)
                .setParameter("type", type)
                .getResultList();

    }

}
