package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void listBoardsDtoMapToBoardsTest() {
        //GIVEN
        TrelloListDto trelloListDto1, trelloListDto2;
        TrelloBoardDto trelloBoardDto1, trelloBoardDto2;
        List<TrelloListDto> treloListDto = new ArrayList<>();
        List<TrelloBoardDto> treloBoardDto = new ArrayList<>();

        trelloListDto1 = new TrelloListDto("1", "trelloListDto1", true);
        trelloListDto2 = new TrelloListDto("2", "trelloListDto2", true);
        treloListDto.add(trelloListDto1);
        treloListDto.add(trelloListDto2);

        trelloBoardDto1 = new TrelloBoardDto("1", "trelloBoardDto1", treloListDto);
        trelloBoardDto2 = new TrelloBoardDto("2", "trelloBoardDto2", treloListDto);
        treloBoardDto.add(trelloBoardDto1);
        treloBoardDto.add(trelloBoardDto2);

        //WHEN
        List<TrelloBoard> mappedTrelloBoardsList = trelloMapper.mapToBoards(treloBoardDto);

        String id1 = mappedTrelloBoardsList.get(0).getId();
        String name1 = mappedTrelloBoardsList.get(0).getName();
        int sizeListOfTrelloBoard1 = mappedTrelloBoardsList.get(0).getLists().size();

        String id2 = mappedTrelloBoardsList.get(1).getId();
        String name2 = mappedTrelloBoardsList.get(1).getName();
        int sizeListOfTrelloBoard2 = mappedTrelloBoardsList.get(1).getLists().size();

        //THEN
        assertEquals("1", id1);
        assertEquals("trelloBoardDto1", name1);
        assertEquals(2, sizeListOfTrelloBoard1);

        assertEquals("2", id2);
        assertEquals("trelloBoardDto2", name2);
        assertEquals(2, sizeListOfTrelloBoard2);
    }

    @Test
    public void listBoardsMapToBoardsDtoTest() {
        //GIVEN
        TrelloList trelloList1, trelloList2;
        TrelloBoard trelloBoard1, trelloBoard2;
        List<TrelloList> treloList = new ArrayList<>();
        List<TrelloBoard> treloBoard = new ArrayList<>();

        trelloList1 = new TrelloList("1", "trelloList1", true);
        trelloList2 = new TrelloList("2", "trelloList2", true);
        treloList.add(trelloList1);
        treloList.add(trelloList2);

        trelloBoard1 = new TrelloBoard("1", "trelloBoard1", treloList);
        trelloBoard2 = new TrelloBoard("2", "trelloBoard2", treloList);
        treloBoard.add(trelloBoard1);
        treloBoard.add(trelloBoard2);

        //WHEN
        List<TrelloBoardDto> mappedTrelloBoardsDtoList = trelloMapper.mapToBoardsDto(treloBoard);

        String id1 = mappedTrelloBoardsDtoList.get(0).getId();
        String name1 = mappedTrelloBoardsDtoList.get(0).getName();
        int sizeListOfTrelloBoard1 = mappedTrelloBoardsDtoList.get(0).getLists().size();

        String id2 = mappedTrelloBoardsDtoList.get(1).getId();
        String name2 = mappedTrelloBoardsDtoList.get(1).getName();
        int sizeListOfTrelloBoard2 = mappedTrelloBoardsDtoList.get(1).getLists().size();

        //THEN
        assertEquals("1", id1);
        assertEquals("trelloBoard1", name1);
        assertEquals(2, sizeListOfTrelloBoard1);

        assertEquals("2", id2);
        assertEquals("trelloBoard2", name2);
        assertEquals(2, sizeListOfTrelloBoard2);
    }

    @Test
    public void listTrelloListDtoMapToTrelloListTest() {
        //GIVEN
        TrelloListDto trelloListDto1, trelloListDto2;
        List<TrelloListDto> treloListDto = new ArrayList<>();

        trelloListDto1 = new TrelloListDto("1", "trelloListDto1", true);
        trelloListDto2 = new TrelloListDto("2", "trelloListDto2", true);
        treloListDto.add(trelloListDto1);
        treloListDto.add(trelloListDto2);
        //WHEN
        List<TrelloList> mappedTrelloListDto = trelloMapper.mapToList(treloListDto);
        String id1 = mappedTrelloListDto.get(0).getId();
        String name1 = mappedTrelloListDto.get(0).getName();
        boolean isClosed1 = mappedTrelloListDto.get(0).isClosed();

        String id2 = mappedTrelloListDto.get(1).getId();
        String name2 = mappedTrelloListDto.get(1).getName();
        boolean isClosed2 = mappedTrelloListDto.get(1).isClosed();

        //THEN
        assertEquals("1", id1);
        assertEquals("trelloListDto1", name1);
        assertEquals(true, isClosed1);

        assertEquals("2", id2);
        assertEquals("trelloListDto2", name2);
        assertEquals(true, isClosed2);
    }

    @Test
    public void listTrelloListMapToTrelloListDtoTest() {
        //GIVEN
        TrelloList trelloList1, trelloList2;
        List<TrelloList> treloList = new ArrayList<>();

        trelloList1 = new TrelloList("1", "trelloList1", true);
        trelloList2 = new TrelloList("2", "trelloList2", true);
        treloList.add(trelloList1);
        treloList.add(trelloList2);

        //WHEN
        List<TrelloListDto> mappedTrelloList = trelloMapper.mapToListDto(treloList);
        String id1 = mappedTrelloList.get(0).getId();
        String name1 = mappedTrelloList.get(0).getName();
        boolean isClosed1 = mappedTrelloList.get(0).isClosed();

        String id2 = mappedTrelloList.get(1).getId();
        String name2 = mappedTrelloList.get(1).getName();
        boolean isClosed2 = mappedTrelloList.get(1).isClosed();

        //THEN
        assertEquals("1", id1);
        assertEquals("trelloList1", name1);
        assertEquals(true, isClosed1);

        assertEquals("2", id2);
        assertEquals("trelloList2", name2);
        assertEquals(true, isClosed2);
    }

    @Test
    public void trelloCardDtoMapToTrelloCardTest() {
        //GIVEN
        TrelloCardDto trelloCardDto;
        trelloCardDto = new TrelloCardDto("trelloCardDto", "descriptionDto", "posDto", "listIdDto");

        //WHEN
        TrelloCard mappedTrelloCardDto = trelloMapper.mapToCard(trelloCardDto);
        String name = mappedTrelloCardDto.getName();
        String description = mappedTrelloCardDto.getDescription();
        String pos = mappedTrelloCardDto.getPos();
        String listId = mappedTrelloCardDto.getListId();

        //THEN
        assertEquals("trelloCardDto", name);
        assertEquals("descriptionDto", description);
        assertEquals("posDto", pos);
        assertEquals("listIdDto", listId);
    }

    @Test
    public void trelloCardMapToTrelloCardDtoTest() {
        //GIVEN
        TrelloCard trelloCard;
        trelloCard = new TrelloCard("trelloCard", "description", "pos", "listId");

        //WHEN
        TrelloCardDto mappedTrelloCard = trelloMapper.mapToCardDto(trelloCard);
        String nameDto = mappedTrelloCard.getName();
        String descriptionDto = mappedTrelloCard.getDescription();
        String posDto = mappedTrelloCard.getPos();
        String listIdDto = mappedTrelloCard.getListId();

        //THEN
        assertEquals("trelloCard", nameDto);
        assertEquals("description", descriptionDto);
        assertEquals("pos", posDto);
        assertEquals("listId", listIdDto);
    }
}