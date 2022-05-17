package andima.movie.service.impl;

import andima.movie.mappers.TVMapper;
import andima.movie.models.dto.TV.TVDto;
import andima.movie.models.entity.Movie;
import andima.movie.models.entity.TV;
import andima.movie.repositories.TVRepository;
import andima.movie.service.interfaces.TVService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TVServiceImpl implements TVService {
    final private TVRepository tvRepository;

    final private TVMapper tvMapper;


    public List<TV> jsonToTV() {
        ObjectMapper mapper = new ObjectMapper();
        List<TVDto> tvDtos;
        List<TV> result = new ArrayList<>();
        try {
            for (int i = 1; i <= 20; i++) {
                tvDtos = Arrays.asList((mapper.readValue(new File("C:\\Users\\slexn\\IdeaProjects\\movie\\src\\main\\json\\tv\\tv" + i + ".json"), TVDto[].class)));
                for (TVDto tvDto : tvDtos) {
                    result.add(tvMapper.toModel(tvDto));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void saveAllTVs() {
        tvRepository.createTable();
        tvRepository.saveAllAndFlush(jsonToTV());
    }

    @Override
    public void deleteAllTVs() {
        tvRepository.deleteAll();
    }

    @Override
    public void dropTVTable() {
        tvRepository.dropTable();
    }

    @Override
    public TV getTV(Long id) {
        return tvRepository.findById(id).isPresent()
                ? tvRepository.findById(id).get()
                : null;
    }

    @Override
    public void saveTV(TV tv) {
        if (tv.getOrigin_country() == null) {
            tv.setOrigin_country("US");
        }
        if (tv.getBackdrop_path() == null) {
            tv.setBackdrop_path("");
        }
        if (tv.getGenre_ids() == null) {
            tv.setGenre_ids("");
        }
        if (tv.getVote_count() == null) {
            tv.setVote_count(0L);
        }
        if (tv.getOriginal_language() == null) {
            tv.setOriginal_language("en");
        }
        if (tv.getOriginal_name() == null) {
            tv.setOriginal_name(tv.getName());
        }
        if (tv.getVote_average() == null) {
            tv.setVote_average(0.0);
        }
        if (tv.getPoster_path() == null) {
            tv.setPoster_path("");
        }
        if (tv.getOverview() == null) {
            tv.setOverview("");
        }
        if (tv.getFirst_air_date() == null) {
            tv.setFirst_air_date("");
        }
        if (tv.getMedia_type() == null) {
            tv.setMedia_type("");
        }
        if (tv.getPopularity() == null) {
            tv.setPopularity(0.0);
        }
        tvRepository.save(tv);
    }

    @Override
    public void updateTV(TV tv) {
        TV tvToUpdate = tvRepository.findById(tv.getId()).get();
        if (tvToUpdate != null) {
            if (tv.getBackdrop_path() != null) {
                tvToUpdate.setBackdrop_path(tv.getBackdrop_path());
            }
            if (tv.getGenre_ids() != null) {
                tvToUpdate.setGenre_ids(tv.getGenre_ids());
            }
            if (tv.getVote_count() != null) {
                tvToUpdate.setVote_count(tv.getVote_count());
            }
            if (tv.getOriginal_language() != null) {
                tvToUpdate.setOriginal_language(tv.getOriginal_language());
            }
            if (tv.getOriginal_name() != null) {
                tvToUpdate.setOriginal_name(tv.getOriginal_name());
            }
            if (tv.getOrigin_country() != null) {
                tvToUpdate.setOrigin_country(tv.getOrigin_country());
            }
            if (tv.getVote_average() != null) {
                tvToUpdate.setVote_average(tv.getVote_average());
            }
            if (tv.getPoster_path() != null) {
                tvToUpdate.setPoster_path(tv.getPoster_path());
            }
            if (tv.getOverview() != null) {
                tvToUpdate.setOverview(tv.getOverview());
            }
            if (tv.getFirst_air_date() != null) {
                tvToUpdate.setFirst_air_date(tv.getFirst_air_date());
            }
            if (tv.getMedia_type() != null) {
                tvToUpdate.setMedia_type(tv.getMedia_type());
            }
            if (tv.getPopularity() != null) {
                tvToUpdate.setPopularity(tv.getPopularity());
            }
            if (tv.getName() != null) {
                tvToUpdate.setName(tv.getName());
            }
        }
        tvRepository.save(tvToUpdate);
    }

    @Override
    public void deleteTV(Long id) {
        tvRepository.deleteById(id);
    }

    @Override
    public void voteTV(Long id, Double vote) {
        TV tv = tvRepository.findById(id).isPresent()
                ? tvRepository.findById(id).get()
                : null;
        if (tv != null) {
            double newVote = tv.getVote_average() * tv.getVote_count() + vote;
            tv.setVote_count(tv.getVote_count() + 1);
            newVote = newVote / tv.getVote_count();
            DecimalFormat df = new DecimalFormat("#.#");
            tv.setVote_average(Double.parseDouble(df.format(newVote)));
            tvRepository.save(tv);
        } else {
            System.out.println("TV with id " + id + " is not exist");
        }
    }

    @Override
    public List<TV> searchTV(String year, String media_type, String title, String lang) {
        return tvRepository.findAll()
                .stream()
                .filter((e) -> {
                    boolean a = true;
                    if (year != null && !Objects.equals(e.getFirst_air_date().length(), 0)) {
                        a = Integer.parseInt(year) <= Integer.parseInt(e.getFirst_air_date().substring(0, 4))
                                && (Integer.parseInt(year) + 9) >= Integer.parseInt(e.getFirst_air_date().substring(0, 4));
                    }
                    return a;
                }).filter(e -> {
                    boolean a = true;
                    if (media_type != null && !Objects.equals(e.getMedia_type(), "")) {
                        a = Objects.equals(e.getMedia_type(), media_type);
                    }
                    return a;
                })
                .filter(e -> {
                    boolean a = true;
                    if (lang != null && !Objects.equals(e.getOriginal_language(), "")) {
                        a = Objects.equals(e.getOriginal_language(), lang);
                    }
                    return a;
                }).filter(e -> {
                    boolean a = true;
                    if (title != null && !Objects.equals(e.getName(), "")) {
                        a = e.getName().toLowerCase().contains(title.toLowerCase());
                    }
                    return a;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TV> trendyTV() {
        return tvRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(TV::getPopularity).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }
}