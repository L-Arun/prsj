package rsj.admin.web.service.impl.user;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.user.UserDao;
import rsj.admin.web.domain.user.User;
import rsj.admin.web.service.user.UserService;

import com.lehecai.core.util.CharsetConstant;
import com.lehecai.core.util.CoreStringUtils;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public void manage(User user){
		userDao.merge(user);
	}


	/**
	 * 多条件分页查询用户
	 * @param userName	用户名
	 * @param name	姓名
	 * @param beginDate	起始创建时间
	 * @param endDate 结束创建时间
	 * @param roleID 角色编号
	 * @param valid	是否有效
	 * @param pageBean
	 * @return
	 */
	@Override
	public List<User> list(String userName, String name, Date beginDate,
			Date endDate, Long roleID, String valid, PageBean pageBean) {
		return userDao.list(userName, name, beginDate, endDate, roleID, valid, pageBean);
	}

	/**
	 * 根据用户编号查询用户
	 * @param ID 用户编号
	 */
	@Override
	public User get(Long ID) {
		return userDao.get(ID);
	}

	@Override
	public void del(User user) {
		userDao.del(user);
	}

	@Override
	public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	@Override
	public User login(String userName, String password) {
		User user = userDao.getByUserName(userName);
		if(user != null && user.getId() != null){
			if(CoreStringUtils.md5(password, CharsetConstant.CHARSET_UTF8).equals(user.getPassword())){
				return user;
			}
		}
		return null;
	}

	/**
	 * 封装多条件查询分页信息
	 * @param userName	用户名
	 * @param name	姓名
	 * @param beginDate	起始创建时间
	 * @param endDate 结束创建时间
	 * @param roleID 角色编号
	 * @param valid	是否有效
	 * @param pageBean
	 * @return
	 */
	@Override
	public PageBean getPageBean(String userName, String name, Date beginDate,
			Date endDate, Long roleID, String valid, PageBean pageBean) {
		return userDao.getPageBean(userName, name, beginDate, endDate, roleID, valid, pageBean);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
