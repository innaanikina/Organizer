package bot.repository;

import bot.entities.BotLogics;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class BotLogicRepositoryClass implements BotLogicsRepository{
    private SessionFactory sessionFactory;
    public BotLogicRepositoryClass() {
    }



    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<BotLogics> findAll() {
        return null;
    }

    @Override
    public List<BotLogics> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BotLogics> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<BotLogics> findAllById(Iterable<Long> longs) {
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
    public void delete(BotLogics entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BotLogics> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends BotLogics> S save(S entity) {
        return null;
    }

    @Override
    public <S extends BotLogics> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BotLogics> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends BotLogics> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends BotLogics> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<BotLogics> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BotLogics getOne(Long aLong) {
        return null;
    }

    @Override
    public BotLogics getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends BotLogics> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BotLogics> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BotLogics> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BotLogics> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BotLogics> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BotLogics> boolean exists(Example<S> example) {
        return false;
    }
}
