package cs6301.g33;

/** 
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MDS {

	static TreeMap<Long, Item> items = new TreeMap<>();
	static TreeMap<Long, Float> suppliers = new TreeMap<>();

	public MDS() {
	}

	public static class Item {
		Pair pair;
		HashSet<Long> description;
		TreeMap<Long, Integer> suppliersItems = new TreeMap<>();

		public Item(Pair p) {
			this.pair = p;
			this.description = null;
		}
		
		 public static Comparator<Item> ElementBasedComparator= new Comparator<Item>() {

		    	public int compare(Item item1, Item item2) {
					if (item1.description.size() > item2.description.size())
						return -1;
					else if (item1.description.size() < item2.description.size())
						return 1;
					else
						return 0;
				}
		    };
		    
		  
		    
		    public static Comparator<Item> PriceBasedComparator= new Comparator<Item>() {

		    	public int compare(Item item1, Item item2) {
					if (item1.pair.price < item2.pair.price)
						return 1;
					else if (item1.pair.price > item2.pair.price)
						return -1;
					else
						return 0;
				}
		    };
	}

	public static class Supplier {
		Long id;
		float reputation;
	}

	public static class Pair {
		long id;
		int price;

		public Pair(long id, int price) {
			this.id = id;
			this.price = price;
		}
	}

	/*
	 * add a new item. If an entry with the same id already exists, the new
	 * description is merged with the existing description of the item. Returns
	 * true if the item is new, and false otherwise.
	 */
	public boolean add(Long id, Long[] description) {
		if (items.containsKey(id)) {
			Item itm = items.get(id);
			for(Long l: description){
				itm.description.add(l);
			}
			return false;
		} else {
			Pair p = new Pair(id, 0);
			Item itm = new Item(p);
			// itm.pair.id=id;
			itm.description = new HashSet<>();
			for(Long l: description){
				itm.description.add(l);
			}
			items.put(id, itm);
			return true;
		}
	}

	/*
	 * add a new supplier (Long) and their reputation (float in [0.0-5.0],
	 * single decimal place). If the supplier exists, their reputation is
	 * replaced by the new value. Return true if the supplier is new, and false
	 * otherwise.
	 */
	public boolean add(Long supplier, float reputation) {
		if (suppliers.containsKey(supplier)) {
			suppliers.put(supplier, reputation);
			return false;
		}
		suppliers.put(supplier, reputation);
		return true;
	}

	/*
	 * add products and their prices at which the supplier sells the product. If
	 * there is an entry for the price of an id by the same supplier, then the
	 * price is replaced by the new price. Returns the number of new entries
	 * created.
	 */
	public int add(Long supplier, Pair[] idPrice) {
		int count = 0;
		if (!suppliers.containsKey(supplier)) {
			suppliers.put(supplier, (float) 0);
		}
		for (Pair p : idPrice) {
			if (items.containsKey(p.id)) {
				Item tmp = items.get(p.id);
				if (tmp.suppliersItems.containsKey(supplier)) {
					tmp.suppliersItems.put(supplier, p.price);
				} else {
					tmp.suppliersItems.put(supplier, p.price);
					count++;
				}
			}
		}
		return count;
	}

	/*
	 * return an array with the description of id. Return null if there is no
	 * item with this id.
	 */
	public Long[] description(Long id) {
		if (items.containsKey(id)) {
			int count=0;
			Item tmp = items.get(id);
			int s = tmp.description.size();
			Long[] result = new Long[s];
			for(Long l: tmp.description){
				//System.out.print(l+" ");
				result[count++]=l;
			}
			//System.out.println();
			return result;
		}
		return null;
	}

	/*
	 * given an array of Longs, return an array of items whose description
	 * contains one or more elements of the array, sorted by the number of
	 * elements of the array that are in the item's description (non-increasing
	 * order).
	 */
	public Long[] findItem(Long[] arr) {
		List<Item> finalItems = new ArrayList<Item>();
		for (Entry<Long, Item> item : items.entrySet()) {
			for (Long desc : item.getValue().description) {
				if (Arrays.asList(arr).contains(desc)) {
					finalItems.add(item.getValue());
					break;
				}
			}
		}
		Item[] items = new Item[finalItems.size()];
		Long[] itemsDes = new Long[finalItems.size()];
		for(int i=0;i<items.length;i++)
			items[i] = finalItems.get(i);
		Arrays.sort(items, Item.ElementBasedComparator);
		int index = 0;
		for (Item i : items)
			itemsDes[index++] = i.pair.id;
		return itemsDes;
	}

	/*
	 * given a Long n, return an array of items whose description contains n,
	 * which have one or more suppliers whose reputation meets or exceeds the
	 * given minimum reputation, that sell that item at a price that falls
	 * within the price range [minPrice, maxPrice] given. Items should be sorted
	 * in order of their minimum price charged by a supplier for that item
	 * (non-decreasing order).
	 */
	public Long[] findItem(Long n, int minPrice, int maxPrice,
			float minReputation) {
		List<Item> finalItems = new ArrayList<Item>();

		for (Entry<Long, Item> item : items.entrySet()) {
			if (item.getValue().description.contains(n)) {
				//System.out.println("##"+ item.getKey());
				if (item.getValue().suppliersItems.size() > 0) {
					for (Entry<Long, Integer> entry : item.getValue().suppliersItems
							.entrySet()) {
						Float rep = suppliers.get(entry.getKey());
						
						if (rep != null
								&& rep >= minReputation
								&& (entry.getValue() > minPrice && entry
										.getValue() < maxPrice)) {
							finalItems.add(item.getValue());
							break;
						}
					}
				}
			}
		}

		Item[] items = new Item[finalItems.size()];
		Long[] itemIds = new Long[finalItems.size()];
		for(int i=0;i<items.length;i++)
			items[i] = finalItems.get(i);
		Arrays.sort(items, Item.PriceBasedComparator);
		int index = 0;
		for (Item i : items)
			itemIds[index++] = i.pair.id;
		return itemIds;

	}

	/*
	 * given an id, return an array of suppliers who sell that item, ordered by
	 * the price at which they sell the item (non-decreasing order).
	 */
	public Long[] findSupplier(Long id) {
		Item item = items.get(id);
		if (item == null || item.suppliersItems == null)
			return null;
		else {
			 int count=0;
			 Long[] result = new Long[item.suppliersItems.keySet().size()]; 
			 Set<Long> st=item.suppliersItems.keySet();
			 for(Long l : st){
				 result[count++]=l;
			 }
			 return result;
		}

	}

	/*
	 * given an id and a minimum reputation, return an array of suppliers who
	 * sell that item, whose reputation meets or exceeds the given reputation.
	 * The array should be ordered by the price at which they sell the item
	 * (non-decreasing order).
	 */
	public Long[] findSupplier(Long id, float minReputation) {
		Item item = items.get(id);
		List<Long> supplierIds = new ArrayList<Long>();
		if(item != null)
		{
			for(Entry<Long,Integer> entry: item.suppliersItems.entrySet())
			{
				if(suppliers.get(entry.getKey()) >= minReputation)
					supplierIds.add(entry.getKey());
			}
			Long[] ids = new Long[supplierIds.size()];
			for(int i=0;i<ids.length;i++)
				ids[i] = supplierIds.get(i);
			return ids;
		}
		return null;
	}

	/*
	 * find suppliers selling 5 or more products, who have the same identical
	 * profile as another supplier: same reputation, and, sell the same set of
	 * products, at identical prices. This is a rare operation, so do not do
	 * additional work in the other operations so that this operation is fast.
	 * Creative solutions that are elegant and efficient will be awarded
	 * excellence credit. Return array of suppliers satisfying above condition.
	 * Make sure that each supplier appears only once in the returned array.
	 */
	public Long[] identical() {
		return null;
	}

	/*
	 * given an array of ids, find the total price of those items, if those
	 * items were purchased at the lowest prices, but only from sellers meeting
	 * or exceeding the given minimum reputation. Each item can be purchased
	 * from a different seller.
	 */
	public int invoice(Long[] arr, float minReputation) {
		int totalPrice = 0;
		for (Long i : arr) {
			Long[] ids =findSupplier(i, minReputation);
			if(ids.length!=0){
				Long idSupplier = findSupplier(i, minReputation)[0];
				Item itm = items.get(i);
				int supplierPrice = itm.suppliersItems.get(idSupplier);
				totalPrice += supplierPrice;
			}else{
				System.out.println("Skipping id "+ i + " not available from seller with at least "+minReputation+" reputation");
			}
		}
		return totalPrice;
	}

	/*
	 * remove all items, all of whose suppliers have a reputation that is equal
	 * or lower than the given maximum reputation. Returns an array with the
	 * items removed.
	 */
	public Long[] purge(float maxReputation) {
		ArrayList<Long> prge = new ArrayList<>();
		Iterator<Entry<Long, Float>> it1 = suppliers.entrySet().iterator();
		while (it1.hasNext()) {
			Entry<Long, Float> e = it1.next();
			if (e.getValue() <= maxReputation) {
				Iterator<Entry<Long, Item>> it2 = items.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<Long, Item> itm = it2.next();
					if (itm.getValue().suppliersItems.containsKey(e.getKey())) {
						prge.add(itm.getKey());
						it2.remove();
					}
				}
				it1.remove();

			}
		}
		return (Long[]) prge.toArray();
	}

	/*
	 * remove item from storage. Returns the sum of the Longs that are in the
	 * description of the item deleted (or 0, if such an id did not exist).
	 */
	public Long remove(Long id) {
		Long result = 0L;
		if (items.containsKey(id)) {
			Item itm = items.get(id);
			for (Long l : itm.description) {
				result += l;
			}
			items.remove(id);
		}
		return result;
	}

	/*
	 * remove from the given id's description those elements that are in the
	 * given array. It is possible that some elements of the array are not part
	 * of the item's description. Return the number of elements that were
	 * actually removed from the description.
	 */
	public int remove(Long id, Long[] arr) {
		int res = 0;
		Item item = items.get(id);
		if(item != null)
		{
			List<Long> des = new LinkedList<Long>(item.description);
			for(Long l: arr)
			{
				Iterator<Long> it = des.iterator();
				while(it.hasNext())
				{
					if(it.next() == l)
					{
						it.remove();
						res++;
					}
				}
			}
			HashSet<Long> desTemp = new HashSet<>(des.size());
			for(int i=0;i<des.size();i++)
				desTemp.add(des.get(i));
			item.description = desTemp;
		}
		return res;
	}

	/*
	 * remove the elements of the array from the description of all items.
	 * Return the number of items that lost one or more terms from their
	 * descriptions.
	 */
	public int removeAll(Long[] arr) {
		int res=0;
		int result = 0;
		for(Entry<Long,Item> item: items.entrySet())
		{
			List<Long> des = new LinkedList<Long>(item.getValue().description);
			for(Long l: arr)
			{
				Iterator<Long> it = des.iterator();
				while(it.hasNext())
				{
					if(it.next() == l)
					{
						it.remove();
						res++;
					}
				}
			}
			if(res>0)
			{
				result++;
				HashSet<Long> desTemp = new HashSet<>(des.size());
				for(int i=0;i<des.size();i++)
					desTemp.add(des.get(i));
				item.getValue().description = desTemp;
			}
		}
		return result-1;
	}
}
