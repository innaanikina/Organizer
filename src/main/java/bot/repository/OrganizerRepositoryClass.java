package bot.repository;

import bot.entities.Organizers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class OrganizerRepositoryClass implements OrganizerRepository {
    @Override
    public List<Organizers> findAll() {
        return null;
    }

    @Override
    public List<Organizers> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Organizers> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Organizers> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Organizers entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Organizers> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Organizers> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Organizers> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Organizers> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Organizers> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Organizers> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Organizers> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Organizers getOne(Long aLong) {
        return null;
    }

    @Override
    public Organizers getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Organizers> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Organizers> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Organizers> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Organizers> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Organizers> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Organizers> boolean exists(Example<S> example) {
        return false;
    }
}
