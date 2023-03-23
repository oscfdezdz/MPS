package es.uma.mps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static es.uma.mps.AdvertisementBoard.MAX_BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdvertisementBoardTest {

    AdvertisementBoard advertisementBoard;

    class DummyAdvertiserDatabase implements AdvertiserDatabase {

        @Override
        public boolean advertiserIsRegistered(String advertiserName) {
            return true;
        }
    }

    class FakePaymentGateway implements PaymentGateway {

        @Override
        public boolean advertiserHasFunds(String advertiserName) {
            return advertiserName.equals("Robin Robot");
        }

        @Override
        public void chargeAdvertiser(String advertiserName) {

        }
    }

    @BeforeEach
    void setup() {
        advertisementBoard = new AdvertisementBoard();
    }

    @Test
    void oneAdAtStart() {
        int expectedValue = 1;
        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void ownerPublication() {
        Advertisement ad = new Advertisement("Title 1", "Text 1", "THE Company");
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();
        int expectedValue = 2;

        advertisementBoard.publish(ad, dummyAdvertiserDatabase, fakePaymentGateway);

        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void withoutFundsPublication() {
        Advertisement ad = new Advertisement("Title 2", "Text 2", "Pepe Gotera y Otilio");
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();
        int expectedValue = 1;

        advertisementBoard.publish(ad, dummyAdvertiserDatabase, fakePaymentGateway);

        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void publication() {
        Advertisement ad = new Advertisement("Title 3", "Text 3", "Robin Robot");
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();

        advertisementBoard.publish(ad, dummyAdvertiserDatabase, fakePaymentGateway);

        assertFalse(advertisementBoard.findByTitle("Title 3").isEmpty());
    }

    @Test
    void notFindDeletedAd() {
        Advertisement adOne = new Advertisement("Title 4", "Text 4", "THE Company");
        Advertisement adTwo = new Advertisement("Title 5", "Text 5", "THE Company");
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();

        advertisementBoard.publish(adOne, dummyAdvertiserDatabase, fakePaymentGateway);
        advertisementBoard.publish(adTwo, dummyAdvertiserDatabase, fakePaymentGateway);

        advertisementBoard.deleteAdvertisement("Title 4", "THE Company");

        assertTrue(advertisementBoard.findByTitle("Title 4").isEmpty());
    }

    @Test
    void ignoreDuplicatePublication() {
        Advertisement adOne = new Advertisement("Title 4", "Text 4", "Robin Robot");
        Advertisement adTwo = new Advertisement("Title 4", "Text 4", "Robin Robot");
        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();
        int expectedValue = 2;

        advertisementBoard.publish(adOne, dummyAdvertiserDatabase, fakePaymentGateway);
        advertisementBoard.publish(adTwo, dummyAdvertiserDatabase, fakePaymentGateway);

        assertEquals(expectedValue, advertisementBoard.numberOfPublishedAdvertisements());
    }

    @Test
    void publicationsLimitReached() {
        AdvertisementBoard spyBoard = spy(new AdvertisementBoard());

        AdvertiserDatabase dummyAdvertiserDatabase = new DummyAdvertiserDatabase();
        PaymentGateway fakePaymentGateway = new FakePaymentGateway();

        Advertisement ad;

        for (int i = 0; i < MAX_BOARD_SIZE - 1; i++) { // Bucle hasta el número máximo de anuncios menos el añadido al inicio
            ad = new Advertisement("Title " + i, "Text " + i, "Robin Robot");
            spyBoard.publish(ad, dummyAdvertiserDatabase, fakePaymentGateway);
        }

        Advertisement timAd = new Advertisement("Title", "Text", "Tim O'Theo");

        assertThrows(AdvertisementBoardException.class, () -> spyBoard.publish(timAd, dummyAdvertiserDatabase, fakePaymentGateway));

        verify(spyBoard, times(1)).publish(timAd, dummyAdvertiserDatabase, fakePaymentGateway);
    }
}