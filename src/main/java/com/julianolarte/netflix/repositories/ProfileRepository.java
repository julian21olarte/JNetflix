package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
