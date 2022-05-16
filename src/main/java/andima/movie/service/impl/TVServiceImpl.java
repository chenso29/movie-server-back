package andima.movie.service.impl;

import andima.movie.mappers.TVMapper;
import andima.movie.models.dto.TV.TVDto;
import andima.movie.models.entity.TV;
import andima.movie.repositories.TVRepository;
import andima.movie.service.interfaces.TVService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
