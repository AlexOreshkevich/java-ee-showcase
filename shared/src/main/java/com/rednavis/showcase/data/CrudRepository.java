package com.rednavis.showcase.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic CRUD API on entity of type E.
 *
 * @param <E>  type of the entity
 * @param <ID> type of the primary key (ID)
 * @linkplain https://github.com/spring-projects/spring-data-commons/blob/master/src/main/java/org/springframework/data/repository/CrudRepository.java
 */
public interface CrudRepository<E extends Serializable, ID extends Serializable> {

  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely.
   *
   * @param entity must not be {@literal null}.
   * @return the saved entity; will never be {@literal null}.
   * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
   */
  <S extends E> S save(S entity);

  /**
   * Saves all given entities.
   *
   * @param entities must not be {@literal null} nor must it contain {@literal null}.
   * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size as the {@literal Iterable}
   * passed as an argument.
   * @throws IllegalArgumentException in case the given {@link Iterable entities} or one of its entities is {@literal null}.
   */
  @SuppressWarnings("unused")
  default <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
    List<S> list = new ArrayList<>();
    entities.forEach(entity -> list.add(save(entity)));
    return list;
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param id must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found.
   * @throws IllegalArgumentException if {@literal id} is {@literal null}.
   */
  E findById(ID id);

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  Iterable<E> findAll();

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities.
   */
  long count();

  /**
   * Deletes the entity with the given id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
   */
  void deleteById(ID id);

  /**
   * Deletes a given entity.
   *
   * @param entity must not be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  void delete(E entity);

  /**
   * Deletes the given entities.
   *
   * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
   * @throws IllegalArgumentException in case the given {@literal entities} or one of its entities is {@literal null}.
   */
  default void deleteAll(Iterable<? extends E> entities) {
    entities.forEach(this::delete);
  }

  /**
   * Deletes all entities managed by the repository.
   */
  void deleteAll();
}
