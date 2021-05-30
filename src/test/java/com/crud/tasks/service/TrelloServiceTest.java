package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    AdminConfig adminConfig;

    @Test
    public void shouldFetchTrelloBoards() {
        //GIVEN
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "name", null);
        List<TrelloBoardDto> listTrelloBoardDto = new ArrayList<>();
        listTrelloBoardDto.add(trelloBoardDto);

        //THNE
        when(trelloClient.getTrelloBoards()).thenReturn(listTrelloBoardDto);
        List<TrelloBoardDto> listOfBoards = trelloService.fetchTrelloBoards();

        //THEN
        assertEquals(1, listOfBoards.size());
    }

    @Test
    public void shouldCreateTrelloCard() {
        //GIVEN
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id","name","url:test");

        //THNE
        when(trelloClient.createNewCard(any())).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("AdminName");

        CreatedTrelloCardDto newCard = trelloService.createTrelloCard(trelloCardDto);

        //THEN
        assertEquals("id", newCard.getId());
        assertEquals("name", newCard.getName());
        assertEquals("url:test", newCard.getShortUrl());
    }
}