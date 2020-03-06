package xyz.gotop.vhr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.gotop.vhr.mapper.HrMapper;
import xyz.gotop.vhr.mapper.HrRoleMapper;
import xyz.gotop.vhr.model.Hr;
import xyz.gotop.vhr.model.HrExample;
import xyz.gotop.vhr.model.HrRoleExample;
import xyz.gotop.vhr.utils.HrUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo HrService
 *
 * @author Wolf-Liu
 * @date 2019/12/22 23:33
 */
@Service
public class HrService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private HrMapper hrMapper;
    @Resource
    private HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在或密码错误");
        }
        hr.setRoles(hrMapper.getRolesByHrId(hr.getId()));
        return hr;
    }

    public List<Hr> getHrsWithRoles() {
        return hrMapper.getHrsWithRoles(HrUtils.getHr().getId());
    }

    public int delete(Integer hrId) {
        return hrMapper.deleteByPrimaryKey(hrId);
    }

    public int update(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    public int deleteAllRolesFromHr(Integer hrId) {
        final HrRoleExample example = new HrRoleExample();
        example.createCriteria()
                .andHridEqualTo(hrId);
        return hrRoleMapper.deleteByExample(example);
    }

    public int deleteRoleFromHr(Integer hrId, List<Integer> rids) {
        final HrRoleExample example = new HrRoleExample();
        example.createCriteria()
                .andHridEqualTo(hrId)
                .andRidIn(rids);
        return hrRoleMapper.deleteByExample(example);
    }

    public int addRolesToHr(Integer hrId, Integer[] rids) {
        return hrRoleMapper.insertManyHrRolesByHrId(hrId, rids);
    }

    public int add(Hr hr) {
        return hrMapper.insertSelective(hr);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateRolesOfHr(Integer hrId, Integer[] rids) {
        deleteAllRolesFromHr(hrId);
        addRolesToHr(hrId, rids);
        return true;
    }

    public List<Hr> getHrsExcludingCurrent(Integer currentId) {
        HrExample example = new HrExample();
        example.createCriteria().andIdNotEqualTo(currentId);
        return hrMapper.selectByExample(example);
    }
}
