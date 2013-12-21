package pl.airborn.gameoflife.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import junitparams.mappers.DataMapper;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Position;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CellMapper implements DataMapper {

    @Override
    public Object[] map(Reader reader) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> objects = Lists.newArrayList();
        try {
            JsonNode rootNode = objectMapper.readTree(reader);
            for (JsonNode testNode : rootNode) {
                JsonNode beforeNode = testNode.get("before");
                Cell[] beforeCells = getCellArray(beforeNode);
                JsonNode afterNode = testNode.get("expected");
                Cell[] afterCells = getCellArray(afterNode);
                int evolutions = testNode.get("evolutions").asInt();
                objects.add(new Object[]{beforeCells, afterCells, evolutions});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objects.toArray();
    }

    private Cell[] getCellArray(JsonNode cellsNode) {
        List<Cell> cells = Lists.newArrayList();
        for (JsonNode cellNode : cellsNode) {
            int x = cellNode.get("x").asInt();
            int y = cellNode.get("y").asInt();
            cells.add(new Cell(new Position(x, y)));
        }
        return cells.toArray(new Cell[cells.size()]);
    }
}
