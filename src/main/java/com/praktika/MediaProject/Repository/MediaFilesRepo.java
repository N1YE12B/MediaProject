package com.praktika.MediaProject.Repository;

import com.praktika.MediaProject.Model.MediaFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface MediaFilesRepo extends JpaRepository<MediaFiles, Integer> {


}
