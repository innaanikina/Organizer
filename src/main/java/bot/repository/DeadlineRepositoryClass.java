package bot.repository;

import bot.entities.Deadlines;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class DeadlineRepositoryClass implements DeadlinesRepository {
    @Override
    public List<Deadlines> findAll() {
        return null;
    }

    @Override
    public List<Deadlines> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Deadlines> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Deadlines> findAllById(Iterable<Long> longs) {
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
    public void delete(Deadlines entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Deadlines> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Deadlines> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Deadlines> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Deadlines> findById(Long aLong) {
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
    public <S extends Deadlines> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Deadlines> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Deadlines> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Deadlines getOne(Long aLong) {
        return null;
    }

    @Override
    public Deadlines getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Deadlines> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Deadlines> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Deadlines> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Deadlines> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Deadlines> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Deadlines> boolean exists(Example<S> example) {
        return false;
    }
}
