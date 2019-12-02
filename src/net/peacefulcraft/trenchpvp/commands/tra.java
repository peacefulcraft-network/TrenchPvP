	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		if(! (command.getName().equalsIgnoreCase("tra"))) { return null; }
		if(! (sender instanceof Player) ) { return null; }
		
		List<String> opts = new ArrayList<String>();
		
		if(args.length > 0) {
			
			switch(args[0]) {
			case "status":
				
				
			break; case "arena":
				
			break;
			}
			
		}else {
			opts.add("status");
			opts.add("arena");
		}
		
		return opts;
	}
