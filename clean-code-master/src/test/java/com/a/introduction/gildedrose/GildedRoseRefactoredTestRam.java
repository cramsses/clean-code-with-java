package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseRefactoredTestRam {
	private static final int MAX_QUALITY = 50;
	private static final String AGED_BRIE = "Aged Brie";
	private static final int EXPIRED_SELLIN = -1;
	private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	private static final int DEFAULT_QUALITY = 3;
	private static final int NOT_EXPIRED_SELLIN = 15;


	@Test
	public void unexpiredDefaultItem_qualityDecreasesBy1() {
		//SETUP
		GildedRose app = 
				createGiledRoseWithOneItem(DEFAULT_ITEM, 
						NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		//INVOKE
		app.updateQuality();
		//VERIFY
		Item expected = new Item(DEFAULT_ITEM, 
				NOT_EXPIRED_SELLIN-1, DEFAULT_QUALITY-1);
		assertItem(expected, app.items[0]);
	}

	
	@Test
	public void expiredDefaultItem_qualityDecreasesBy2() {
		//SETUP
		GildedRose app = 
				createGiledRoseWithOneItem(DEFAULT_ITEM, 
						EXPIRED_SELLIN, DEFAULT_QUALITY);
		//INVOKE
		app.updateQuality();
		//VERIFY
		Item expected = new Item(DEFAULT_ITEM, 
				EXPIRED_SELLIN-1, DEFAULT_QUALITY-2);
		assertItem(expected, app.items[0]);
	}
	
	
	@Test
	public void unexpiredAgedBrie_qualityIncreadeBy1() {
		GildedRose app = 
				createGiledRoseWithOneItem(AGED_BRIE, 
						NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		
		app.updateQuality();

		Item expected = new Item(AGED_BRIE, 
				NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY +1 );
		assertItem(expected, app.items[0]);
		
	}
	
	@Test
	public void expiredAgedBrie_qualityIncreadeBy2() {
		GildedRose app = 
				createGiledRoseWithOneItem(AGED_BRIE, 
						EXPIRED_SELLIN, DEFAULT_QUALITY);
		
		app.updateQuality();
		
		Item expected = new Item(AGED_BRIE, 
				EXPIRED_SELLIN - 1, DEFAULT_QUALITY +2 );
		assertItem(expected, app.items[0]);
	}
	
	@Test
	public void unexpiredAgedBrie_maxQuality() {
		GildedRose app = 
				createGiledRoseWithOneItem(AGED_BRIE, 
						NOT_EXPIRED_SELLIN, MAX_QUALITY);
		
		app.updateQuality();
		
		Item expected = new Item(AGED_BRIE, 
				NOT_EXPIRED_SELLIN - 1, MAX_QUALITY );
		assertItem(expected, app.items[0]);
	}

	
	/*Utility methods	*/
	
	/**
	 * @param expected
	 * @param actual
	 */
	private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}

	/**
	 * @param itemType
	 * @param sellin
	 * @param quality
	 * @return
	 */
	private GildedRose createGiledRoseWithOneItem(String itemType, int sellin, int quality) {
		Item item = new Item(itemType, sellin, quality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}
}