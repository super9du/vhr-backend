package xyz.gotop.vhr.service;

import javafx.geometry.Pos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.gotop.vhr.mapper.PositionMapper;
import xyz.gotop.vhr.model.Position;
import xyz.gotop.vhr.model.PositionExample;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Demo PositionService
 *
 * @author Wolf-Liu
 * @date 2020/1/16 0:42
 */
@Service
public class PositionService {
    @Resource
    private PositionMapper positionMapper;

    public List<Position> getAll() {
        final PositionExample example = new PositionExample();
        return positionMapper.selectByExample(example);
    }

    public int add(Position position) {
        position.setCreatedate(new Date());
        position.setEnabled(true);
        return positionMapper.insertSelective(position);
    }

    public int update(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public int delete(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public int deleteMany(Integer[] ids) {
        return positionMapper.deleteManyByIds(ids);
    }
}
