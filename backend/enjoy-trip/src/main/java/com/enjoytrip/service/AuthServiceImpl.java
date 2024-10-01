package com.enjoytrip.service;

import java.sql.SQLException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enjoytrip.dto.auth.User;
import com.enjoytrip.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserMapper userDao;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void createUser(User user) throws SQLException {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userDao.create(user.getId(), user.getEmail(), user.getPassword(), user.getName());
	}

	@Override
	public void deleteUser(User deleteUser) throws SQLException {
		String deleteUserId = deleteUser.getId();
		userDao.delete(deleteUserId);
	}

	@Override
	public void updateUser(User updateUser) throws SQLException {
		// Logic for compare update user password would be changed
		String encodeNewPassword = passwordEncoder.encode(updateUser.getPassword());
		updateUser.setPassword(encodeNewPassword);

		int updateRow = userDao.update(updateUser.getId(),
				updateUser.getEmail(),
				updateUser.getPassword(),
				updateUser.getName());

		if(updateRow == 0) {
			// TODO : raise Exception no row updated
		}
	}

	@Override
	public List<User> findAll() throws SQLException {
		return userDao.findAll();
	}

	@Override
	public User findById(String id) throws SQLException {
		return userDao.findById(id);
	}

	@Override
	public void logout(User loginUser) throws SQLException {
		userDao.expireToken(loginUser.getId());
	}

	@Override
	public User login(User user) throws SQLException {
		return userDao.findByIdAndPassword(user.getId(),user.getPassword());
	}
}
