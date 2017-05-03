package com.yz.service.imp;

import com.yz.dao.MediaDao;
import com.yz.model.Media;
import com.yz.model.UserRole;
import com.yz.service.MediaService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("mediaService")
public class MediaServiceImp implements MediaService {

	@Resource
	private MediaDao mediaDao;


	public void add(Media media) {
		mediaDao.save(media);
	}


	public void delete(Media media) {
		mediaDao.delete(media);
	}

	public void deleteById(int id) {
		mediaDao.deleteById(id);
	}

	public void update(Media media) {
		mediaDao.update(media);
	}

	public List<Media> getMedias() {
		return mediaDao.getMedias();
	}

	public Media loadById(int id) {
		return mediaDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

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
			p = new Object[]{'%' + convalue + '%'};
		}

		return mediaDao.getUniqueResult(queryString, p);
	}

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
			p = new Object[]{'%' + convalue + '%'};
		}
		return mediaDao.pageList(queryString, p, page, size);
	}

	public List<Media> loadInjurycaseByTypeAndPid(int mtype, Integer inid) {
		String queryString = "from Media mo where mo.mtype=:mtype and mo.injurycase.id=:inid";
		String[] paramNames = new String[]{"mtype", "inid"};
		Object[] values = new Object[]{mtype, inid};
		return mediaDao.queryList(queryString, paramNames, values);
	}

	public String setInjurycaseIdsSql(String queryString, String convalue, int mtype) {
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
		} else {
			queryString += " and mo.id in (0)";
		}
		return queryString;
	}

	public List<Media> loadJudgeByJid(int jid) {
		String queryString = "from Media mo where mo.mtype!=1 and mo.judge.id=:jid";
		String[] paramNames = new String[]{"jid"};
		Object[] values = new Object[]{jid};
		return mediaDao.queryList(queryString, paramNames, values);
	}
}
