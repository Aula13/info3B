package org.wms.model.warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import org.wms.model.common.ICRUDLayer;

/**
 * Warehouse model
 * This model store warehouse structure
 * 
 * This model is synchronized though a semaphore(1)
 * 
 * This model provide observable methods
 * for get signal about warehouse cells modifications
 * 
 * @author Stefano Pessina, Daniele Ciriello
 *
 */
public class Warehouse extends Observable {
	
	public List<WarehouseLine> lines = new ArrayList<>();
	
	protected ICRUDLayer<WarehouseLine> linePersistenceLayer;
	protected ICRUDLayer<WarehouseShelf> shelfPersistenceLayer;
	protected ICRUDLayer<WarehouseCell> cellPersistenceLayer;
	
	public Warehouse(ICRUDLayer<WarehouseLine> linePersistenceLayer,
			ICRUDLayer<WarehouseShelf> shelfPersistenceLayer,
			ICRUDLayer<WarehouseCell> cellPersistenceLayer) {
		super();
		this.linePersistenceLayer = linePersistenceLayer;
		this.shelfPersistenceLayer = shelfPersistenceLayer;
		this.cellPersistenceLayer = cellPersistenceLayer;
		
		initWarehouseStructure();
	}
	
	protected void initWarehouseStructure() {
		Optional<List<WarehouseLine>> optLines = linePersistenceLayer.selectAll();
		if(optLines.isPresent())
			lines = optLines.get();
	}
	
	public List<WarehouseLine> getUnmodifiableLines() {
		return Collections.unmodifiableList(lines);
	}
}