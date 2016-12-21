package com.yz.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IMediaDao;
import com.yz.model.Media;
import com.yz.model.UserRole;
import com.yz.service.IMediaService;

@Component("mediaService")
public class MediaServiceImp implements IMediaService {
	private IMediaDao mediaDao;

	public IMediaDao getMediaDao() {
		return mediaDao;
	}

	@Resource
	public void setMediaDao(IMediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#add(com.yz.model.Media)
	 */
	public void add(Media media) throws Exception {
		mediaDao.save(media);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#delete(com.yz.model.Media)
	 */
	public void delete(Media media) {
		mediaDao.delete(media);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		mediaDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#update(com.yz.model.Media)
	 */
	public void update(Media media) {
		mediaDao.update(media);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#getMedias()
	 */
	public List<Media> getMedias() {
		return mediaDao.getMedias();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#loadById(int)
	 */
	public Media loadById(int id) {
		return mediaDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#getPageCount(int,
	 *      java.lang.String, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	// 后台管理-获取总记录数
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Media mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}

		return mediaDao.getUniqueResult(queryString, p);
	}

	public Media getMediaByMedianame(String medianame) {
		String queryString = "from Media mo where mo.name=:medianame";
		String[] paramNames = new String[] { "medianame" };
		Object[] values = new Object[] { medianame };
		return mediaDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IMediaServiceImp#queryList(int, java.lang.String,
	 *      int, int)
	 */
	public List<Media> queryList(int con, String convalue, UserRole userRole,
			int page, int size) {
		String queryString = "from Media mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";

			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return mediaDao.pageList(queryString, p, page, size);
	}

	public Media getMediaById(Integer upmediaid) {
		// TODO Auto-generated method stub
		return mediaDao.getMediaById(upmediaid);
	}

	public List<Media> loadByTypeAndPid(int jtype, Integer pid) {
		// TODO Auto-generated method stub
		String queryString = "from Media mo where mo.jtype=:jtype and mo.person.id=:pid";
		String[] paramNames = new String[] { "jtype", "pid" };
		Object[] values = new Object[] { jtype, pid };
		return mediaDao.queryList(queryString, paramNames, values);
	}

	public List<Media> loadClueByTypeAndPid(int jtype, Integer cid) {
		String queryString = "from Media mo where mo.jtype=:jtype and mo.clue.id=:cid";
		String[] paramNames = new String[] { "jtype", "cid" };
		Object[] values = new Object[] { jtype, cid };
		return mediaDao.queryList(queryString, paramNames, values);
	}

	public List<Media> loadInjurycaseByTypeAndPid(int mtype, Integer inid) {
		String queryString = "from Media mo where mo.mtype=:mtype and mo.injurycase.id=:inid";
		String[] paramNames = new String[] { "mtype", "inid" };
		Object[] values = new Object[] { mtype, inid };
		return mediaDao.queryList(queryString, paramNames, values);
	}

	public String setInjurycaseIdsSql(String queryString,String convalue, int mtype) {
		// TODO Auto-generated method stub
		List<Media> medias = new ArrayList<Media>();
		String queryMediaString = "from Media mo where mo.mtype=" + mtype
				+ " and mo.title like '%" + convalue + "%' ";

		String ids = "";
		
		medias = mediaDao.queryList(queryMediaString);

		if (medias != null && medias.size() > 0) {
			Set<Integer> idsSet = new HashSet<Integer>();
			for (int i = 0; i < medias.size(); i++) {
				idsSet.add(medias.get(i).getInjurycase().getId());
			}

			for (Integer id : idsSet) {
				if (!id.equals("")) {
					ids = ids + id + ",";
				}
			}
			String lastChar = "";
			do {
				lastChar = ids.substring(ids.length() - 1, ids.length());
				if (lastChar.equals(",")) {
					ids = ids.substring(0, ids.length() - 1);
				}
			} while (lastChar.equals(","));
			
			if (ids != null) {
				ids = ids.replace(" ", "");
				queryString += " and mo.id in (" + ids + ")";
			} else {
				queryString += " and mo.id in (0)";
			}
		}else
		{
			queryString += " and mo.id in (0)";
		}
		return queryString;
	}
}
