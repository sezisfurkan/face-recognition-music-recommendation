package project.frmr.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import project.frmr.dto.SongDTO;

import java.util.List;

public interface SongController {

    @ApiOperation("Add new data")
    public SongDTO save(@RequestBody SongDTO user);

    @ApiOperation("login")
    public ResponseEntity<?> login(HttpSession session) ;

    @ApiOperation("Find by Id")
    public SongDTO findById(@PathVariable("id") String id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") String id);

    @ApiOperation("Find all data")
    public List<SongDTO> list();

    @ApiOperation("Pagination request")
    public Page<SongDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public SongDTO update(@RequestBody SongDTO dto, @PathVariable("id") String id);

    @ApiOperation("Get Song By emotionId")
    String getSongApiKeyByEmotionId(String emotionId);

}
