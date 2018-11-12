package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

public class InMemoryUserService implements UserService {

	private Map<Long, User> users = new HashMap<>();
	private long nextId = 1L;

	@Override
	public User findOne(Long id) {

		return users.get(id);
	}

	@Override
	public List<User> findAll() {

		return new ArrayList<User>(users.values());
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(nextId++);
		}
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public List<User> save(List<User> users) {
		List<User> ret = new ArrayList<>();

		for (User u : users) {
			User saved = save(u);
			if (saved != null) {
				ret.add(saved);
			}
		}

		return ret;
	}

	@Override
	public User delete(Long id) {
		User user = users.get(id);
		if (user != null) {
			users.remove(id);
		}
		return user;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			delete(id);
		}

	}

	@Override
	public List<User> findByName(String firstName) {
		List<User> ret = new ArrayList<>();

		for (User u : users.values()) {
			if (firstName.equals(u.getFirstName())) {
				ret.add(u);
			}
		}
		return ret;
	}

}
