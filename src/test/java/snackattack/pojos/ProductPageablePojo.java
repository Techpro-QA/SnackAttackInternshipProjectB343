package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPageablePojo implements Serializable {
	private int pageNumber;
	private int pageSize;
	private ProductSortPojo sort;
	private int offset;
	private boolean paged;
	private boolean unpaged;

	public void setPageNumber(int pageNumber){
		this.pageNumber = pageNumber;
	}

	public int getPageNumber(){
		return pageNumber;
	}

	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}

	public int getPageSize(){
		return pageSize;
	}

	public void setSort(ProductSortPojo sort){
		this.sort = sort;
	}

	public ProductSortPojo getSort(){
		return sort;
	}

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setPaged(boolean paged){
		this.paged = paged;
	}

	public boolean isPaged(){
		return paged;
	}

	public void setUnpaged(boolean unpaged){
		this.unpaged = unpaged;
	}

	public boolean isUnpaged(){
		return unpaged;
	}

	@Override
 	public String toString(){
		return 
			"PageablePojo{" + 
			"pageNumber = '" + pageNumber + '\'' + 
			",pageSize = '" + pageSize + '\'' + 
			",sort = '" + sort + '\'' + 
			",offset = '" + offset + '\'' + 
			",paged = '" + paged + '\'' + 
			",unpaged = '" + unpaged + '\'' + 
			"}";
		}
}