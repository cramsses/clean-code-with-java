package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseRefactoredTestRam {
	private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	private static final int DEFAULT_QUALITY = 3;
	private static final int NOT_EXPIRED_SELLIN = 15;

	/**
	 * Method to test the variation in quality of the item for the non expired
	 * Item.
	 * 
	 * The quality should decrease by 1 when the item is not expired
	 * and sell in should decrease by 1.
	 * 
	 */
	@Test
	public void testUpdateQualityDefault1() {
		//SETUP
		GildedRose app = 
				createGiledRoseWithOneItem(DEFAULT_ITEM, 
						NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		
		//INVOKE
		app.updateQuality();
		
		//VERIFY
		Item expected = new Item(DEFAULT_ITEM, 
				NOT_EXPIRED_SELLIN-1, DEFAULT_QUALITY-1);
		//Expected vs Actual
		assertItem(expected, app.items[0]);
	}

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

	/**
	 * Method to test the variation in quality of the item for the non expired
	 * Item.
	 * 
	 * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
	 * 
	 */
	@Test
	public void testUpdateQualityForExpiredItem() {
		Item item = new Item(DEFAULT_ITEM, -1, 3);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(DEFAULT_ITEM, app.items[0].name);
		assertEquals(-2, app.items[0].sellIn);
		assertEquals(1, app.items[0].quality);
	}
}