package project.frmr.controller.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.frmr.controller.SongController;
import project.frmr.dto.SongDTO;
import project.frmr.mapper.SongMapper;
import project.frmr.service.SongService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin
public class SongControllerImpl implements SongController {

    @Autowired
    SongService songService;
    @Autowired
    SongMapper songMapper;

    @Override
    public SongDTO save(SongDTO user) {
        return null;
    }

    @Override
    public ResponseEntity<?> login(HttpSession session) {
        return null;
    }

    @Override
    public SongDTO findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<SongDTO> list() {
        return null;
    }

    @Override
    public Page<SongDTO> pageQuery(Pageable pageable) {
        return null;
    }

    @Override
    public SongDTO update(SongDTO dto, String id) {
        return null;
    }
}
