package es.uma.mps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static es.uma.mps.AdvertisementBoard.BOARD_OWNER;
import static es.uma.mps.AdvertisementBoard.MAX_BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Óscar Fernández Díaz
 */

class AdvertisementBoardTest {

    private AdvertisementBoard advertisementBoard;
    private AdvertiserDatabase advertiserDatabase;
    private PaymentGateway paymentGateway;

    class DummyAdvertiserDatabase implements AdvertiserDatabase {

        @Override
        public boolean advertiserIsRegistered(String advertiserName) {
            return true;
        }
    }

    class FakePaymentGateway implements PaymentGateway {

        @Override
        public boolean advertiserHasFunds(String advertiserName) {
            return advertiserName.equals(BOARD_OWNER);
        }

        @Override
        public void chargeAdvertiser(String advertiserName) {
            // Does not affect board owner
        }
    }

    @BeforeEach
    void setup() {
        advertisementBoard = new AdvertisementBoard();
        advertiserDatabase = mock(AdvertiserDatabase.class);
        paymentGateway = mock(PaymentGateway.class);
    }

    @Test
    void initiallyHasOneAdvertisement() {
        int expectedValue = 1;
        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void addAdvertisementByOwner() {
        Advertisement ad = new Advertisement("Title 1", "Text 1", BOARD_OWNER);
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();
        int expectedValue = 2;

        advertisementBoard.publish(ad, dummyAdvertiserDatabase, fakePaymentGateway);

        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void addAdvertisementWithoutFunds() {
        Advertisement ad = new Advertisement("Title 2", "Text 2", "Pepe Gotera y Otilio");
        int expectedValue = 1;

        when(advertiserDatabase.advertiserIsRegistered(ad.advertiser)).thenReturn(true);
        when(paymentGateway.advertiserHasFunds(ad.advertiser)).thenReturn(false);

        advertisementBoard.publish(ad, advertiserDatabase, paymentGateway);

        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void addValidAdvertisement() {
        Advertisement ad = new Advertisement("Title 3", "Text 3", "Robin Robot");
        int expectedValue = 2;

        when(advertiserDatabase.advertiserIsRegistered(ad.advertiser)).thenReturn(true);
        when(paymentGateway.advertiserHasFunds(ad.advertiser)).thenReturn(true);

        advertisementBoard.publish(ad, advertiserDatabase, paymentGateway);

        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void addTwoAdvertisementsByOwnerAndDeleteOne() {
        Advertisement adOne = new Advertisement("Title 4", "Text 4", BOARD_OWNER);
        Advertisement adTwo = new Advertisement("Title 5", "Text 5", BOARD_OWNER);
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();
        int expectedValue = 2;

        advertisementBoard.publish(adOne, dummyAdvertiserDatabase, fakePaymentGateway);
        advertisementBoard.publish(adTwo, dummyAdvertiserDatabase, fakePaymentGateway);

        advertisementBoard.deleteAdvertisement("Title 4", BOARD_OWNER);

        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
        assertFalse(advertisementBoard.findByTitle("Title 4").isPresent());
    }

    @Test
    void addAdvertisementThatAlreadyExists() {
        Advertisement ad = new Advertisement("Title 4", "Text 4", "Robin Robot");
        int expectedValue = 2;

        when(advertiserDatabase.advertiserIsRegistered(ad.advertiser)).thenReturn(true);
        when(paymentGateway.advertiserHasFunds(ad.advertiser)).thenReturn(true);

        advertisementBoard.publish(ad, advertiserDatabase, paymentGateway);
        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());

        advertisementBoard.publish(ad, advertiserDatabase, paymentGateway);
        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void addAdvertisementWhenBoardIsFull() {
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();

        for (int i = 0; i < MAX_BOARD_SIZE; i++) {
            advertisementBoard.publish(new Advertisement("Title " + i, "Text " + i, BOARD_OWNER), dummyAdvertiserDatabase, fakePaymentGateway);
        }

        when(advertiserDatabase.advertiserIsRegistered(BOARD_OWNER)).thenReturn(true);
        when(paymentGateway.advertiserHasFunds(BOARD_OWNER)).thenReturn(true);

        assertThrows(AdvertisementBoardException.class, () -> advertisementBoard.publish(new Advertisement("Title 21", "Text 21", "Tim O'Theo"), advertiserDatabase, paymentGateway));
    }
}