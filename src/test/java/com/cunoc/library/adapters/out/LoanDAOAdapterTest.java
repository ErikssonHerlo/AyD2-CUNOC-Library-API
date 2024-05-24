package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.adapters.out.entities.LoanEntity;
import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.adapters.out.repositories.BookRepository;
import com.cunoc.library.adapters.out.repositories.LoanRepository;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dto.LoanDTO;
import com.cunoc.library.application.dto.LoanResponseDTO;
import com.cunoc.library.domain.models.enums.LoanStatus;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoanDAOAdapterTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoanDAOAdapter loanDAOAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(1L);

        when(loanRepository.findById(anyLong())).thenReturn(Optional.of(loanEntity));

        Optional<LoanEntity> result = loanDAOAdapter.find(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindById() {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(1L);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("12345");
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        loanEntity.setBook(bookEntity);
        loanEntity.setUser(userEntity);
        loanEntity.setLoanDate(new Date());
        loanEntity.setStatus(LoanStatus.lent);
        loanEntity.setCreatedAt(new Date());
        loanEntity.setUpdatedAt(new Date());

        when(loanRepository.findById(anyLong())).thenReturn(Optional.of(loanEntity));

        Optional<LoanResponseDTO> result = loanDAOAdapter.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        assertEquals("12345", result.get().isbn_code());
        assertEquals("testuser", result.get().username());
    }




    @Test
    void testSave() {
        LoanDTO loanDTO = new LoanDTO("12345", "testuser", new Date());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("12345");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(bookEntity));
        when(userRepository.findById(anyString())).thenReturn(Optional.of(userEntity));
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(new LoanEntity());

        LoanResponseDTO result = loanDAOAdapter.save(loanDTO);

        assertNotNull(result);
    }

    @Test
    void testDeleteById() {
        doNothing().when(loanRepository).deleteById(anyLong());

        loanDAOAdapter.deleteById(1L);

        verify(loanRepository, times(1)).deleteById(1L);
    }



    @Test
    void testHasActiveLoans() {
        when(loanRepository.existsByUserUsernameAndStatus(anyString(), any(LoanStatus.class))).thenReturn(true);

        boolean result = loanDAOAdapter.hasActiveLoans("testuser");

        assertTrue(result);
    }

    @Test
    void testUpdateReturnDate() {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(1L);

        when(loanRepository.findById(anyLong())).thenReturn(Optional.of(loanEntity));
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(loanEntity);

        loanDAOAdapter.updateReturnDate(1L, new Date());

        verify(loanRepository, times(1)).save(any(LoanEntity.class));
    }
}
